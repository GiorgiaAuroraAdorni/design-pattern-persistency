# Design Pattern Persistency
Eighth assignment for Software Engineering course @ USI 19/20.

#### Repository

The source code is available on GitLab at
[https://github.com/GiorgiaAuroraAdorni/design-pattern-persistency](https://github.com/GiorgiaAuroraAdorni/design-pattern-persistency).

#### Contributors

This assignment has been developed by Giorgia Adorni.

## Installation

```
$ git clone https://github.com/GiorgiaAuroraAdorni/design-pattern-persistency.git
```

## Design of the architecture

### Model

![model](/Users/giorgia/design-pattern-persistency/img/model.png )

### View

The web application consists of four pages:

- the **home page** where it is possible to choose if login, register, view the users list or search for a user;

  ![home](/img/home.png)

- the **login page** is used to login into the system;

  ![login](/img/login.png)

- the **registration page** allows the registration of new users;

  ![registration](/img/registration.png)

- the last page contains the **user list** of all the registered users and where it is possible to **search users** by name, address or best friend.
  ![view](/img/view.png)

  ![userlist](/img/userlist.png)

  ![search](//img/search.png)

  ![workingsearch](//img/workingsearch.png)

  ![errorsearch](/img/errorsearch.png)

### Controller

![controller](/img/controller.png)

## Patterns

- The **Front Controller** patter has been used to reduce the amount of repeated boilerplate code. It handles all the requests.
- The **Template View** pattern has been adopted to speed up the implementation of the user interface. An example of template is the page with the users list.
- The **Active Record** pattern handles the mapping of app data to data sources. Each DB record correspond to actual business logic entities.
