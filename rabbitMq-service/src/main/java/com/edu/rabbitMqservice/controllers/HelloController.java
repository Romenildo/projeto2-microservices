

import java.util.List;
import java.util.Optional;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping(value = "/Alunoes")
public class AlunoController {

    @Autowired
    private AlunoRepository AlunoRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    public String EXCHANGE_NAME;


    @GetMapping
    public List<Aluno> getAlunos() {
        return AlunoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Aluno> getAlunoById(@PathVariable Long id) {
        return AlunoRepository.findById(id);
    }

    @PostMapping(consumes = {"*/*"})
    public Aluno createAluno(@RequestBody Aluno Aluno) {

        try {
           EmailDTO emailDTO = new EmailDTO("romenildo@gmail.com", "Aluno Criado", "Aluno foi criado com sucesso");
            String json = new ObjectMapper().writeValueAsString(emailDTO);
            Message message = MessageBuilder.withBody(json.getBytes())
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .build();
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, "", message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return AlunoRepository.save(Aluno);
    }

    @PutMapping("/{id}")
    public Aluno updateAluno(@PathVariable("id") Long id, @RequestBody Aluno Aluno) {
        
        try {
           EmailDTO emailDTO = new EmailDTO("romenildo@gmail.com", "Aluno Editado", "O Aluno foi editado com sucesso");
            String json = new ObjectMapper().writeValueAsString(emailDTO);
            Message message = MessageBuilder.withBody(json.getBytes())
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .build();
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, "", message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Aluno.setId(id);
        return AlunoRepository.save(Aluno);
    }

    @DeleteMapping("/{id}")
    public void deleteAluno(@PathVariable Long id) {
        AlunoRepository.delete(AlunoRepository.findById(id).get());
    }
}
