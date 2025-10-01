# Meet Yoda!
Developed by Vito, who does things in the last minute.

That's where Yoda comes in. Yoda is a simple **command-line task management app** that helps you keep track of your todos, deadlines, and events.  
With Yoda, managing tasks is as fun as learning from the Jedi Master himself.

Source: [CS2113-AY2526-S1 DOCS](https://nus-cs2113-ay2526s1.github.io/website/admin/ip-overview.html)

---

## 🚀 Getting Started

**Prerequisites**: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
    1. Click `Open`.
    1. Select the project directory, and click `OK`.
    1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/yoda/Yoda.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   ----------------------------------------------
    ___  _ ____  ____  ____ 
    \  \///  _ \/  _ \/  _ \
     \  / | / \|| | \|| / \|
     / /  | \_/|| |_/|| |-||
    /_/   \____/\____/\_/ \|
    
    ------------------- AWAKENS ------------------
   ```

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.
