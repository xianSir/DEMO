package learn.springcore;

import com.xks.conf.config;
import learn.person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * @author xks
 * @date 2019-07-23
 */
public class main {
    public static void main(String[] args) {
        RestTemplate template = new RestTemplate();
    }

    private static void spel() {
        SpelExpressionParser expressionParser = new SpelExpressionParser();
        Expression raw = expressionParser.parseExpression("'hello world'.concat('!  first')");
        Object value = raw.getValue();
        System.out.println(value);

        person person = new person("xiaobai", 10);
        Expression name = expressionParser.parseExpression("name");
        System.out.println(name.getValue(person));

        SpelParserConfiguration configuration = new SpelParserConfiguration(true, true);
        SpelExpressionParser parser = new SpelExpressionParser(configuration);
        ArrayList<Object> list = new ArrayList<>();
        list.add("6666");
        Expression expression = parser.parseExpression("[0]");
        System.out.println(expression.getValue(list));
    }

    /**
     * 基于xml启动
     */
    private static void xmlstart() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("conf/spring-context.xml");
        context.start();
        System.out.println("spring启动结束");
    }

    /**
     * 基于注解启动spring
     */
    private static void annotionStart() {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        configApplicationContext.register(config.class);
    }
}
