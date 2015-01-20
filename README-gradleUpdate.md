
# README for gradle update to JStock

## Checkout

This section describes how to check out the source for jstock.  Navigate to [https://github.com/yccheok/jstock](https://github.com/yccheok/jstock) and clone the project using your git client.

At the moment the work for this is on the `AddGradleBuild` branch.  So you need to check this out after the project has been cloned.  On the command-line execute this:

    git checkout AddGradleBuild

## Setup

This section describes how to setup jstock driven by gradle in the various IDEs. 

Generally, Gradle needs to be installed first.  Download from [https://www.gradle.org/downloads](https://www.gradle.org/downloads).

Next define the GRADLE_HOME environment variable.

Add $GRADLE_HOME/bin to the PATH.

It is not essential for general (non-developer) users of jstock to install gradle.  Instead they can use the `gradlew` (or `gradlew.bat`) script which will download a run-time version into the project.  However this isn't powerful enough to do all that is required.

### Gradlew

This section describes how `gradlew` can be used to setup jstock.

TODO

### Eclipse

The Eclipse-Gradle plugin needs to be installed - `Help > Marketplace` and type 'gradle` in to the search box.

`Right-click in the Project Explorer > Import > Gradle Project`.  Browse to the project and click `Build Model` and `Finish`.

`Right-click in the Project Explorer > Gradle > Refresh All`.  All dependencies should be resolved and there should be no errors (assuming the last commit had none).  Testing and development can be performed from here.

### Netbeans

At this stage, the jstock project has both a Netbeans project and a Gradle one and the Netbeans project wins.  However there will come a time when the netbeans project will be dropped and in this case what you do is this.  

First instal the Netbeans Gradle plugin - `Tools > Plugins > Available Plugins` and type 'gradle' in the search box.
 
Then what you do is nothing special.  Netbeans will allow one to open the gradle project and cleans and build will run the standard commands.  However not everything that needs to be done can and in which case one needs to work on the command-line.  See below.

### InteliJ

TODO though I'm sure that InteliJ treats Gradle projects as first-class citizens and doesn't require plugins to be installed.

## Running

This section describes how to run jstock driven by gradle in the various IDEs and from the command-line.

Be aware that all versions of JStock - the currently released version or a fork such as this, will use the same database.  If you want to preserve it in some state be sure to copy or move it out of the way.  On the Mac the database is in `.jstock` in the users home directory.  In Windows and Linux the same should be true.

### Eclipse

TODO.  It should be as simple as the following though it didn't work.

`Run > Configurations > gradle run > new > Give it a name > Apply > Run`.  However this errors and the workspace seems to get in the way.  TODO to work this out.

Alternatively you can run it directly through the Gradle eclipse plugin:
# Right-Click on Project > Gradle > Tasks Quick Launcher
# Make sure the jstock (jstockOehmSmithFork) is selected in the `project` drop-down
# Type `run` for the task and hit enter.  JStock should start
 
### Netbeans

Netbeans should honour the gradle project and `Run > Run Project` should work though this is untested.  TODO is to confirm this.

### InteliJ

Given that gradle is a first-class citizen in InteliJ, jstock should run as things normally do in Intelij.  TODO is to confirm this.
  
### Command-Line

The `build.gradle` includes the `application` plugin and thus inherits the `run` task.  This will run it from the source (not the `.jar`):

    gradle clean run
    
## Building

This section describes how to build a jar for distribution. 

Once you have the distribution you should be able to change into its directory and type:

    java -jar jstock-x.y.jar

And it should run.

### Eclipse

There is no specific eclipse way to run what is required and the only way is to use the Gradle Tasks Quick Launcher (`project > Gradle > Tasks Quick Launcher`) and run the same commands as under *gradle* below (but without the `gradle` prefix).

Eclipse doesn't seem to be smart enough to pick up the Main class specified through the `build.gradle` file and when one `Project > Right-click > Run > Run As Application` it asks which Main class to use.  It does however work well when testing. 

Due to this *Tasks Quick Launcher* all command line possibilities can be performed through Eclipse as well.  See below.

### Netbeans

I don't know how to run Gradle commands from Netbeans.  Building, Testing and Running will work.  However at this stage, the Netbeans project files remain present and they take preference over Gradle in Netbeans.  So jstock acts as it did before and `build` will build to a `<project>/dist` directory.

When the netbeans project is no longer there, build may run a `gradle build` and this will write everything to the `build` directory.  You are better off jumping to the command-line for this.

### InteliJ

TODO

### Command-Line

    gradle clean build

This runs the tests, creates a `dist/jstock-x.y.jar` and creates a `dist/lib/` with all the dependencies in it.

    gradle clean assemble

is the same except it doesn't run the tests (which is somtimes nice).

## Installers

This section describes how to create an installer for distribution for Windows, MacOSX and Linux. 

### Eclipse

One is better to work from the command-line for this, or use the `Gradle Tasks Quick Launcher` as discussed under *Building > Eclipse* above.

### Netbeans

One is better to work from the command-line for this.

### InteliJ

One is better to work from the command-line for this

### Command-Line

There are two levels for this - simple installer for various platforms, and a native installer for the given platform.

#### Simple installer

Gradle out of the box will create a simple installer (using the `application` plugin).

`gradle distTar` will create a `jstock-x.y.tar` file in the `./build/distributions/` directory.  And.

`gradle distZip` will create a `jstock-x.y.zip` file in the `./build/distributions/` directory.

Both contain the same files under a `jstock-x.y/` base directory.  Windows can handle both `.zip` and `.tar` files though `.zip` is more common.  The reverse is true for Linux and MacOSX.  The files / directories are:

    lib/
       jstock-x.y.jar
       ... all the dependencies
    bin/
        jstock-x.y.sh    # Linux and MacOSX
        jstock-x.y.bat   # Windows

Its certainly nothing fancy and doesn't install the application as users are used to these days.  Though it will get the management team out of a sticky situation in emergency cases.

#### Native installer

Jstock currently has a way to create Windows and Linux installers though its a manual process run after the build completes.

A new version of the installer will be written that simplifies this and has it running through gradle.  This is coming soon.  This new version will also create native installers for MacOSX, Windows and Linux.
