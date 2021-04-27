package learn;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author xks
 * @date 2019-07-23
 */
public class main {
    public static void main(String[] args) {
        String baseUrl = "https://example.org";
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(baseUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.TEMPLATE_AND_VALUES);
//        ServletUriComponentsBuilder.
//        WebClient client = WebClient.builder().uriBuilderFactory(factory).build();
    }

}
