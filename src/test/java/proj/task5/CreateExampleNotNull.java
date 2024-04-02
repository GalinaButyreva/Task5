package proj.task5;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import proj.task5.ProductExample.Model.InstanceArrangement;
import proj.task5.ProductExample.Model.ProdExample;
import proj.task5.ProductExample.Service.*;
import proj.task5.Repository.Tpp_productRepo;
import proj.task5.model.Tpp_product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = {Step_21_PE.class, Tpp_productRepo.class})
@SpringBootApplication(scanBasePackages = "proj.task5")
public class CreateExampleNotNull {
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
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        modelExample.setInstanceId(null);
        modelExample.setProductType("НСО");
        modelExample.setProductCode("03.012.002");
        modelExample.setRegisterType("03.012.002_47533_ComSoLd");
        modelExample.setMdmCode("15");
        modelExample.setContractNumber("122/FK");
        modelExample.setContractDate(LocalDate.parse("2024-03-29", dateTimeFormatter));
        modelExample.setPriority(Integer.valueOf("00"));
        modelExample.setContractId(11);
        modelExample.setBranchCode("0022");
        modelExample.setIsoCurrencyCode("800");
        modelExample.setUrgencyCode("00");
        List<InstanceArrangement> argLst = new ArrayList<>();

        InstanceArrangement arg = new InstanceArrangement();
        arg.setNumber("124/RT");
        arg.setOpeningDate(LocalDate.parse("2024-03-29", dateTimeFormatter));
        argLst.add(arg);

        arg = new InstanceArrangement();
        arg.setNumber("456/RT");
        arg.setOpeningDate(LocalDate.parse("2024-04-10", dateTimeFormatter));
        argLst.add(arg);

        modelExample.setInstanceArrangement(argLst);

    }
    ProdExample modelExample = new ProdExample();


    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.name", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    @Qualifier("21_PE")
    Step_21_PE step_21_pe;

    @Autowired
    @Qualifier("23_PE")
    Step_23_PE step_23_pe;



    @Autowired
    Maker_PExample makerPExample;

    @Autowired
    Tpp_productRepo tpp_productRepo;

    @Test
    @Description("Проверка шага 21  ТЗ")
    void step_PETest(){
        Tpp_product tpp_product;
        //tpp_product =tpp_productRepo.findFirst().orElse(null);
        makerPExample.setModelProdExample(modelExample);
        ResponseEntity<?> responce = makerPExample.execute();
        tpp_product = tpp_productRepo.findFirstByNumber(modelExample.getContractNumber());

        Assertions.assertNotNull(tpp_product);

        modelExample.setInstanceId(tpp_product.getId());
        responce = step_21_pe.execute(modelExample);
        Assertions.assertNull(responce);

    }

    @Test
    @Description("Проверка создания записей в Agreement")
    void maker_PExampleTest() {
        Tpp_product tpp_product;
        makerPExample.setModelProdExample(modelExample);
        ResponseEntity<?> responce = makerPExample.execute();
        tpp_product = tpp_productRepo.findFirstByNumber(modelExample.getContractNumber());

        Assertions.assertNotNull(tpp_product);

        modelExample.setInstanceId(tpp_product.getId());

        responce =  step_23_pe.execute(modelExample);

        Assertions.assertNotNull(responce);
        Assertions.assertEquals(HttpStatus.CREATED.value(), responce.getStatusCode().value());

    }
}
