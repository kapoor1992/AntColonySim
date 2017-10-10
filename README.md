# AntColonySim
Java ant colony simulation using several independent state machines.

DESCRIPTION

This is an ant colony simulation. Ants start by randomly roaming around for food. If they find food, they return home with it and breed. If they bred, they roam for water. Once they find water, they drink it and roam for food once again. If at any point an ant runs into poison, it immediately dies. The state diagram for this process has been included in this directory. Newly bred ants also start by looking for food. The simulation goes on until all ants have run into poison, and at that point, the user can close the program. Food, water, and poison has been completely randomly placed at the beginning (meaning that poison could spawn right over the ant house/colony). The user can specify between 1-15 starting ants.



INSTRUCTIONS

Please note that the simulation has only been tested in Terminal on Mac OSX Sierra. To run it:

	1) cd to just outside the "antsimulation" folder
	2) type "javac antimulation/AntSimulationMain.java"
	3) type "java antsimulation.AntSimulationMain"
	4) enter a number between 1-15 (inclusive)

Usage video: https://youtu.be/DXQlMFuPSac
