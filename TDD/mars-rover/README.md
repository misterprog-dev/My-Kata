## Project
> You’re part of the team that explores Mars by sending remotely controlled vehicles to the surface of the planet. Develop an API that translates the commands sent from earth to instructions that are understood by the rover.

## Requirements
- You are given the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
- The rover receives a character array of commands.
- Implement commands that move the rover forward/backward (f,b).
- Implement commands that turn the rover left/right (l,r).
- Implement wrapping at edges. But be careful, planets are spheres.
- Implement obstacle detection before each move to a new square. If a given sequence of commands encounters an obstacle, the rover moves up to the last possible point, aborts the sequence and reports the obstacle.

## How does it work?
> <u>Step 1</u> : Enter the planet dimension<br>
  <u>Rule</u>: A valid form is x y and it is numerics values<br>
  <u>Example</u>: 5 4 <br>

> <u>Step 2</u>: Enter the obstacles positions <br>
  <u>Rule</u> : A valid form is X1 Y1 X2 Y2...Xn Yn and it is numerics values <br>
  <u>Example</u> : 2 3 3 1 1 1 <br>

><u>Step 3</u>: Enter the rover initial position <br>
  <u>Rule</u> : A valid form is X Y D (X, Y for coordinates are numerics and D for Direction in [N,S,E,W]) <br>
  <u>Example</u>: 2 2 S

> <u>Step 4</u>: Enter the rover commands <br>
  <u>Rule</u> : A valid form is C1C2C3..Cn (Cx in [F,B,L,R]). <br>
  <u>Example</u> : FBBLR

## Launch le project
```bash
mvn compile exec:java
```

## Launch tests
```bash
mvn test
```