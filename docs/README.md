# Yoda User Guide 📝


```
___  _ ____  ____  ____ 
\  \///  _ \/  _ \/  _ \
 \  / | / \|| | \|| / \|
 / /  | \_/|| |_/|| |-||
/_/   \____/\____/\_/ \|
```

Yoda is a simple **command-line task management app** that helps you keep track of your todos, deadlines, and events.  
With Yoda, managing tasks is as fun as learning from the Jedi Master himself.

---
# Table of Contents

- [🚀 Quick Start](#-quick-start)
- [📖 Commands](#-commands)
    - [Adding a todo : `todo`](#adding-a-to-do--todo)
    - [Adding a deadline : `deadline`](#adding-a-deadline--deadline)
    - [Adding an event: `event`](#adding-an-event--event)
    - [Listing All Tasks : `list`](#listing-all-tasks--list)
    - [Marking a Task : `mark`](#marking-a-task--mark)
    - [Unmarking a Task : `unmark`](#unmarking-a-task--unmark)
    - [Deleting a Task : `delete`](#deleting-a-task--delete)
    - [Finding Tasks : `find`](#finding-tasks--find)
    - [Exit the Program : `bye`](#exit-the-program--bye)
- [💾 File Storage](#-file-storage)
- [🧑‍💻 Example Session](#-example-session)

---

## 🚀 Quick Start

1. **Run the program**
   ```
   java -jar Yoda.jar
   ```


2. **Yoda greets you**
   ```
   ----------------------------------------------
    ___  _ ____  ____  ____ 
    \  \///  _ \/  _ \/  _ \
     \  / | / \|| | \|| / \|
     / /  | \_/|| |_/|| |-||
    /_/   \____/\____/\_/ \|
    
    ------------------- AWAKENS ------------------
   ```

3. **Start talking to your new friend**:
   ```
   Do or do not what shall I help you with? >
   ```

---

## 📖 Commands

### Adding a To-Do : `todo`
Adds a To-Do task to the list. A To-Do can contain only a description.

Format:
```
todo <description>
```
Example:
```
todo read book
```

### Adding a Deadline : `deadline`
Adds a Deadline task to the list. A Deadline can contain a description and an end date/time.

Format:
```
deadline <description> /by <end>
```
Example:
```
deadline submit report /by Friday
```

### Adding an Event : `event`
Adds a Event task to the list. A Event can contain a description, start date/time, and end date/time.
Format:
```
event <description> /from <start> /to <end>
```
Example:
```
event project meeting /from Mon 2pm /to Mon 4pm
```

### Listing All Tasks : `list`

Shows a list of all tasks in the task list.

Format:
```
list
```

### Marking a Task : `mark`

Marks a task with X, to flag it as finished.

Format:
```
mark <task_number>
```

### Unmarking a Task : `unmark`

Unmarks a task, to flag it as unfinished. (Tasks are unfinished by default)

Format:
```
unmark <task_number>
```

### Deleting a Task : `delete`
*(You will be asked to confirm before deletion.)*

Format:
```
delete <task_number>
```


### Finding Tasks : `find`
Finds tasks which contain any of the given keywords.
- The search is case-sensitive.
- Only tasks with descriptions contain the keywords exactly.

Format:
```
find <keyword>
```
Example:
`find cs2113` will show a task containing `do cs2113 ip`, but not `I love CS2113`

### Exit the Program : `bye`

Exits the program.

Format:
```
bye
```

---

## 💾 File Storage
- Tasks are saved automatically in `data/user.txt`.
- When you restart the program, your previous tasks will be loaded.

---

## 🧑‍💻 Example Session

```
----------------------------------------------
___  _ ____  ____  ____ 
\  \///  _ \/  _ \/  _ \
 \  / | / \|| | \|| / \|
 / /  | \_/|| |_/|| |-||
/_/   \____/\____/\_/ \|
                        
------------------- AWAKENS ------------------
Welcome back youngling! It is a pleasure to see you again
Do or do not what shall I help you with? > todo buy milk
----------------------------------------------
Successfully added: 
[T][ ] buy milk
----------------------------------------------
Do or do not what shall I help you with? > list
----------------------------------------------
You have 1 tasks:
1. [T][ ] buy milk
----------------------------------------------
Do or do not what shall I help you with? > mark 1
----------------------------------------------
Affirmative! Marked have been the task:
[T][X] buy milk
----------------------------------------------
Do or do not what shall I help you with? > bye

Do or do not, I shall say goodbye.
------------- PROGRAM TERMINATED -------------
```