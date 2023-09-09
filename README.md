[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/x6ckGcN8)
# 3500 PA05 Project Repo

# App Pitch
<p>
Use Bujo Pig Bullet Journal to expand your horizons. Based off the late BujoPig's renowned journaling habits,
we have brought you his organizational skills as an application! You can keep track of your weekly commitments
through tasks and events. Modeled after BujoPig's journal, there is a taskbar on the left hand side. Unlike a
written journal, you can edit your tasks and delete them at a whim. You can also keep track of how many commitments
by viewing the stats block in the right hand corner. In addition, we designed a colorful UI for you to enjoy whilst
journaling. To keep BujoPig's legacy alive, please support our application by buying and using the app! If we get 
supporters on Patreon, we will release new features such as password lock, tested GUI, and custom themes!
</p>

# Screenshots

## Loading Splash Screen
![loading](https://github.com/CS-3500-OOD/pa05-bujopig/assets/123038068/e086fe25-732e-4e87-93f6-d9d236f8efc8)

## Welcome Screen
![welcome](https://github.com/CS-3500-OOD/pa05-bujopig/assets/123038068/8cdec540-9532-4745-adaf-8609601dde21)

## Week View
![weekview](https://github.com/CS-3500-OOD/pa05-bujopig/assets/123038068/461306c5-1e5c-42f9-9cec-6deb0d403db7)

## Mini-Viewer
![miniviewer](https://github.com/CS-3500-OOD/pa05-bujopig/assets/123038068/d7ff5c6e-4c20-4941-89c6-a07a2ea3233f)

## New Week Window
![newweek](https://github.com/CS-3500-OOD/pa05-bujopig/assets/123038068/e1e50bdb-7cea-4f9b-adc1-37bf0b07c183)

## Task Creation/Edit Window
![newtask](https://github.com/CS-3500-OOD/pa05-bujopig/assets/123038068/81248426-db28-4f58-bfed-477526d99982)

## Event Creation/Edit Window
![newevent](https://github.com/CS-3500-OOD/pa05-bujopig/assets/123038068/aea699a1-f750-46e0-a3db-9eee4664853d)

## Edit Preferences Window
![edit](https://github.com/CS-3500-OOD/pa05-bujopig/assets/123038068/ce8d2aa3-203f-4c92-9a40-b51d5088bbc7)

## About Window
![about](https://github.com/CS-3500-OOD/pa05-bujopig/assets/123038068/a1de42be-0e22-48ba-bdb0-6c03e970542a)

# SOLID Principle Application:

## Single Responsibility Principle
<p>
Our program applies the SRP in several ways, the most prominent being with having a separate controller for each
of the distinct scene of our program. Each controller implements the controller interface with a run method defined,
however each controller class is specialized to handle only one scene. For example, our WelcomeScene controller
class is responsible for handling user inputs on the welcome screen and passing it on to another controller
when the scene switches.
</p>

## Open Closed Principle
<p>
Our program is open to adding new scenes but closed for being modified. If a developer were to extend this application,
they would add a scene by creating an FXML file and its associated controller. This is how we made our pages extensible.
Furthermore, if a developer wanted to add more than just tasks and events, they could utilize the Item abstract class
to define a new type of Item without modifying current structures. 
</p>

## Liskov Substitution Principle
<p>
Our program applies the LSP within our SceneChanger class, specifically within the switchToScene method. This method
takes in a path to an FXML scene and a Controller which is set to control the scene after the new scene is loaded.
By taking in a Controller, any class implementing the Controller interface can be passed into this method, allowing
the scene to be controlled by any Controller subclass allowing for flexible scene switches.
</p>

## Interface Segregation Principle
<p>
Our program applies the interface segregation principle through the Controller interface. Each controller implements
the run method, and does not need to implement further methods outside the run method in order to function.
Though some controllers may have other methods, the interface does not include these, thereby separating the interfaces
appropriately.
</p>

## Dependency Inversion Principle
<p>
Our program applies dependency inversion by relying upon abstract classes and interfaces as opposed to concrete classes.
A prime example of this is when we utilized a Controller interface in the sceneSwitch method as opposed to any concrete
implementation. A second example is when we utilized the abstract Item class to display a popup window. Because both
Tasks and Events can show a popup menu, this popup took in an Item class instead.
</p>

# Further Extension
<p>
In order to further extend our project, we could easily add a password lock. Because adding a scene is extendable,
as in, it would only require a new FXML file and a controller for that scene, we could easily add such a lock screen
to prevent the user from moving on. Furthermore, we would hash the password in the .bujo file so that users cannot
simply open the file with a text editor to ascertain the password. 
</p>

# Attributions
[Skull](https://media.tenor.com/g1bZgt4-tL4AAAAC/skull.gif)

[Mr. Krabs](https://static.wikia.nocookie.net/spongebob/images/7/7b/Krabs_artwork.png/revision/latest/scale-to-width-down/350?cb=20220807045807)

