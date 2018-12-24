# manhattan-distance
This is a simple console program in Scala that calculates the [Manhattan Distance](https://en.wikipedia.org/wiki/Taxicab_geometry) from a given positive integer to the center of a two-dimensional grid where numbers are monotone increasing, starting from 1, and are added in counter-clockwise fashion as per the example below:

```
17 16 15 14 13
18  5  4  3 12
19  6  1  2 11
20  7  8  9 10
21 22 23 -> ...
```

## Running one example
You can run the app to calculate the distance of the number 368078:

```
$ sbt "run 368078"
[...]
[info] Running manhattan.App 368078
The manhattan distance of 368078 is 371
[success] Total time: 1 s, completed Dec 24, 2018 5:12:17 PM
```


## Implementation
The implemented algorithm has time complexity of O(1), as it only needs a handful of simple arithmetic operations applied on the input number. It relies on the fact that the bottom right diagonal is comprised of elements from the sequence (2i+1)^2, where i = 0, 1, 2... The solution diagram below illustrates how the coordinates of the number n can be obtained in constant time by calculating the nearest element in the bottom right diagonal, then the offset of n within its segment in the outer square depicted below:

![solution diagram](solution_diagram.jpg)


## Testing

You can run unit tests as below:

```
$ sbt test
[...]
[info] ManhattanDistanceSpec:
[info] The method distance()
[info]   when given a number from 1 to 1089
[info]   - should return the manhattan distance to the center
[info]   when given a non-positive number
[info]   - should throw an IllegalArgumentException
[info] the method distanceOfCoordinates()
[info]   when given a pair of coordinates
[info]   - should return the sum of the absolute values of the coordinates
[info] Run completed in 1 second, 63 milliseconds.
[info] Total number of tests run: 3
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 3, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
[success] Total time: 3 s, completed Dec 24, 2018 4:47:42 PM
```
