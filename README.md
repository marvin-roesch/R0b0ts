# R0b0ts

A mod which brings modular robots to Minecraft.

## Features
- Mainly all about creating robots
  - Robots can do useful stuff
  - Different modules: Define function (Currently crafting and item collection)
  - Different chipsets: Define range of operation (Currently basic (8 blocks), advanced (16 blocks) and expert (32 blocks))
- Small robot pet called "BuddyBot", mainly for ingame documentation

## Detailed information
### Items
###### Blank module:
Crafted like this (S=Stone, I=Iron ingot, R=Redstone):
```
ISI
SRS
ISI
```
Used as base for the other modules.

###### Blank chipset:
Crafted like this (D=Cactus green, G=Gold ingot, R=Redstone):
```
RGR
GDG
RGR
```
Used as base for the other chipets.

### Blocks
###### Factory controller
Crafted like this (D=Cactus green, G=Glass pane, R=Redstone, I=Iron):
```
IRI
DGD
IRI
```
Used as main block of the robot factory.

###### Factory frame
Crafted like this (S=Stone, I=Iron):
```
ISI
S S
ISI
```
Used as building block in the robot factory.
Has connected textures and is actually purely decorative.

###### Factory glass
Crafted like this (S=Stone, I=Iron, G=Glass):
```
ISI
S S
ISI
```
Used as building block in the robot factory,
but not necessarily needed. 
Has connected textures and is actually purely decorative.

### Entities
###### BuddyBot
Used as ingame documentation and pet.

###### Robot
Core part of the mod. Currently like any player just
with a custom skin, but that's subject to change in the future.

### The robot factory
The robot factory is a 3x3x4 blocks big multiblock structure
which consists of a factory controller (C), factory frames (F)
and (but not necessarily) factory glass (G):
**Layer 1:**
```
FFF
FFF
FFF
```
**Layer 2 (Empty space is air, G can be replaced with F):**
```
FGF
G G
FCF
```
**Layer 3 (Empty space is air, G can be replaced with F):**
```
FGF
G G
FGF
```
**Layer 4:**
```
FFF
FFF
FFF
```

### Creating a robot
To create a robot, build the factory 
and put your desired module, chipset 
and armor for the robot in the slots. 
Then hit "Start" and wait 5 seconds 
until the robot is built. To place 
it down in your world then, take 
the item and right click a block 
with it. The block should be an 
inventory for most modules.

### Modules & chipsets in detail
###### Crafting module
Crafted with the blank module alongside a workbench.
Right click to set recipe.
When the robot is attached to an inventory, it'll
search for the ingredients of the recipe and if
available, it'll put the output in the inventory.

###### Collecting module
Crafted with the blank module alongside a hopper.
When the robot is attached to an inventory, it'll
search for items in the chipet's range around the
inventory block and if it finds something, it'll
get it and put it inside the inventory.
The range is a cube with a volume of
range * range * 2 blocks.

###### Basic chipset
Crafted with the blank chipset alongside a piece of redstone.
When attached to a robot, the robot will have a range of 8 blocks.

###### Advanced chipset
Crafted with the basic chipset alongside a piece of redstone.
When attached to a robot, the robot will have a range of 16 blocks.

###### Expert chipset
Crafted with the advanced chipset alongside a piece of redstone.
When attached to a robot, the robot will have a range of 32 blocks.