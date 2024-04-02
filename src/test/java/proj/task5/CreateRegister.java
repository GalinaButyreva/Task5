package proj.task5;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import proj.task5.ProductRegistr.Model.ProdRegistr;
import proj.task5.ProductRegistr.Service.Step_1_PR;
import proj.task5.ProductRegistr.Service.Step_2_PR;
import proj.task5.ProductRegistr.Service.Step_3_PR;
import proj.task5.Repository.Tpp_product_registerRepo;
import proj.task5.model.Tpp_product_register;
import proj.task5.service.CreateTppProductRegister;

@SpringBootTest(classes = {Step_2_PR.class, Step_1_PR.class, Tpp_product_registerRepo.class})
@SpringBootApplication(scanBasePackages = "proj.task5")
public class CreateRegister{
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void setUp() {
        modelRegister.setInstanceId(10L);
        modelRegister.setRegistryTypeCode("03.012.002_47533_ComSoLd");
    }

    @Autowired
    Tpp_product_registerRepo tppProductRegisterRepo;
    @Autowired
    Step_2_PR step_2_pr;
    @Autowired
    Step_1_PR step_1_pr;
    @Autowired
    Step_3_PR step_3_pr;

    @Autowired
    CreateTppProductRegister createTppProductRegister;

    ProdRegistr modelRegister = new ProdRegistr();
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.name", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }
    @Test
    @Description("Проверка 1-го шага ТЗ")
    void Step_1_PRTest(){
        modelRegister.setInstanceId(null);
        ResponseEntity<?> responce = step_1_pr.execute(modelRegister);
        Assertions.assertNotNull(responce);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responce.getStatusCode().value());
        modelRegister.setInstanceId(10L);
        responce = step_1_pr.execute(modelRegister);
        Assertions.assertNull(responce);
    }


    @Test
    @Description("Проверка 2-го шага ТЗ")
    void Step_2_PRTest(){
       // modelRegister.setInstanceId(10L);
       // modelRegister.setRegistryTypeCode("03.012.002_47533_ComSoLd");

        ResponseEntity<?>  responce = step_2_pr.execute(modelRegister);
        System.out.println(responce);
        Assertions.assertNull(responce);

    }

    @Test
    @Description("Проверка 3-го шага ТЗ проверка наличия записи в tpp_ref_product_register")
    void Step_3_PRTest(){
        //modelRegister.setInstanceId(10L);
        //modelRegister.setRegistryTypeCode("03.012.002_47533_ComSoLd");

        ResponseEntity<?>  responce = step_3_pr.execute(modelRegister);
        System.out.println(responce);
        Assertions.assertNull(responce);
        modelRegister.setRegistryTypeCode("03.012.002_47533_ComS");
        responce = step_3_pr.execute(modelRegister);
        Assertions.assertNotNull(responce);
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), responce.getStatusCode().value());

    }

    @Test
    @Description("Тестируем добавление записи в регистр")
    void createTppProductRegisterTest(){

        //modelRegister.setRegistryTypeCode("03.012.002_47533_ComSoLd");
        //modelRegister.setInstanceId(10L);

        //createTppProductRegister.setProdRegistr(modelRegister);
        Tpp_product_register tpp_product_register = createTppProductRegister.create_rec_table(modelRegister);
        Assertions.assertNotNull(tpp_product_register);
        // Теперь второй шаг должен свалиться
        ResponseEntity<?>  responceBad = step_2_pr.execute(modelRegister);

         Assertions.assertNotNull(responceBad);
         Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responceBad.getStatusCode().value());
    }



}
