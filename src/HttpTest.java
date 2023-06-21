import todo.Address;
import todo.Company;
import todo.Geo;
import todo.User;

import java.io.IOException;

public class HttpTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        User user = new User(3, "Leonard Volodarchuk", "LEO", "kenzorleo@gmail.com",
                new Address("Laboratorniy", "6", "Kyiv", "011000",
                        new Geo(-55.6677, 99.8877)),"(063) 575-86-45", "go.it",
                new Company("HelloWorld", "hi ti all", "zzzzz123"));

        HttpSend.createUser(user);

        HttpSend.putUser(user, 3 );

        HttpSend.deleteUser(6);

        HttpSend.sendGetAllUsers();

        HttpSend.sendGetUserId(3);

        HttpSend.sendGetUsername("Valerii_Vakhovskiy");

        HttpSendTask2.allCommentsLastId(5);

        HttpSendTask3.openTaskForUser(5);

    }
}
