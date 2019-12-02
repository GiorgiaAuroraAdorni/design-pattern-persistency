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

  ![home](/Users/giorgia/design-pattern-persistency/img/home.png)

- the **login page** is used to login into the system;

  ![login](/Users/giorgia/design-pattern-persistency/img/login.png)

- the **registration page** allows the registration of new users;

  ![registration](/Users/giorgia/design-pattern-persistency/img/registration.png)

- the last page contains the **user list** of all the registered users and where it is possible to **search users** by name, address or best friend.
  ![view](/Users/giorgia/design-pattern-persistency/img/view.png)

  ![userlist](/Users/giorgia/design-pattern-persistency/img/userlist.png)

  ![search](/Users/giorgia/design-pattern-persistency/img/search.png)

  ![workingsearch](/Users/giorgia/design-pattern-persistency/img/workingsearch.png)

  ![errorsearch](/Users/giorgia/design-pattern-persistency/img/errorsearch.png)

### Controller

![controller](/Users/giorgia/design-pattern-persistency/img/controller.png)

## Patterns

- The **Front Controller** patter has been used to reduce the amount of repeated boilerplate code. It handles all the requests.
- The **Template View** pattern has been adopted to speed up the implementation of the user interface. An example of template is the page with the users list.
- The **Active Record** pattern handles the mapping of app data to data sources. Each DB record correspond to actual business logic entities.