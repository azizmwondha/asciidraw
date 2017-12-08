# AsciiDraw
A small Java library for composing ASCII art. AsciiDraw allows the user to create images using ASCII characters.

## Example code
``` 
Ascii ascii = new Ascii(24, 12);
ascii.fill('.');
ascii.pen('\\');
ascii.line(0, 0, 24, 12);

ascii.pen('#');
ascii.box(2, 2, 22, 10);

Oval oval = new Oval(18, 6);
oval.move(3, 3);
oval.pen(' ');
ascii.add(oval);

ascii.update();
```

Printing the output would produce the following

``` ascii.print(); ```

``` 
\\......................
..\\....................
..####################..
..####           #####..
..##   ##########   ##..
..#  ##############  #..
..#  ##############  #..
..##   ##########   ##..
..#####          #####..
..####################..
....................\\..
......................\\ ```


##### 