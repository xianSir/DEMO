package learn;

import com.xks.annotation.mark;
import com.xks.mineFace;
import org.springframework.stereotype.Component;

/**
 * @author xks
 * @date 2019-07-23
 */
@Component
public class person implements mineFace {
    String name;
    int age;

    public person() {
    }

    public person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void get() {
        System.out.println("接口方法执行");
    }

    public void doget() {
        System.out.println("接口方法执行 doget");
    }
}
