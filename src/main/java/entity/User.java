package entity;

public class User implements BaseEntity {

    private int id;
    private String firstName;
    private String lastName;
    private String emailLogin;
    private String password; //TODO CHANGE TO CHAR ARRAY
    private RoleUser roleUser;
    private String phoneUser;

    User(int id, String firstName, String lastName, String emailLogin, String password, RoleUser roleUser, String phoneUser) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailLogin = emailLogin;
        this.password = password;
        this.roleUser = roleUser;
        this.phoneUser = phoneUser;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmailLogin() {
        return this.emailLogin;
    }

    public String getPassword() {
        return this.password;
    }

    public RoleUser getRoleUser() {
        return this.roleUser;
    }

    public String getPhoneUser() {
        return this.phoneUser;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailLogin(String emailLogin) {
        this.emailLogin = emailLogin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleUser(RoleUser roleUser) {
        this.roleUser = roleUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
    }

    public String toString() {
        final var stringBuilder = new StringBuilder("User(id=");
        stringBuilder.append(this.getId())
                .append(", firstName=")
                .append(this.getFirstName())
                .append(", lastName=")
                .append(this.getLastName())
                .append(", emailLogin=")
                .append(this.getEmailLogin())
                .append(", password=")
                .append(this.getPassword())
                .append(", roleUser=")
                .append(this.getRoleUser())
                .append(", phoneUser=")
                .append(this.getPhoneUser());
        return stringBuilder.toString();
    }

    public static class UserBuilder {
        private int id;
        private String firstName;
        private String lastName;
        private String emailLogin;
        private String password;
        private RoleUser roleUser;
        private String phoneUser;

        UserBuilder() {
        }

        public UserBuilder id(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder emailLogin(String emailLogin) {
            this.emailLogin = emailLogin;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder roleUser(RoleUser roleUser) {
            this.roleUser = roleUser;
            return this;
        }

        public UserBuilder phoneUser(String phoneUser) {
            this.phoneUser = phoneUser;
            return this;
        }

        public User build() {
            return new User(id, firstName, lastName, emailLogin, password, roleUser, phoneUser);
        }
    }
}
