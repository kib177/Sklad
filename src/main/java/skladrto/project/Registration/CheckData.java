package skladrto.project.Registration;

public class CheckData {

    public String data(String firstname, String secondname, String login, String email, String password, String password2) {
        { // проверка вводимых строк пользователем, не реализованно
            if (firstname.equals("")) return "Введите Имя";
            else if (secondname.equals("")) return "Введите фамилию";
            else if (login.equals("")) return "Введите логин";
            else if (email.equals("")) return "Введите Почту";
            else if (password.equals("")) return "Введите пароль";
            else if (password2.equals("")) return "Повторите пароль";
        }
        return "";
    }
}
