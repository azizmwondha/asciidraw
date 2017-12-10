# ASCIIdraw
A small Java library for composing ASCII art. ASCIIdraw allows the user to create images using ASCII characters.

## Example code
``` 
Ascii ascii = new Ascii(24, 12); // A canvas for ASCII art
ascii.fill('.');
ascii.pen('\\');
ascii.line(0, 0, 24, 12); // Line top-left to bottom-right

ascii.pen('#');
ascii.box(2, 2, 22, 10); // A 20x8 box at 2, 2

Oval oval = new Oval(18, 6);
oval.move(3, 3);
oval.pen(' '); // Use spaces to draw the oval
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
......................\\ 
```


## Basic ASCII operations

ASCIIdraw API provides the following basic drawing operations

Operation | Description 
--- | --- 
`plot(x, y)` | Plot at location (x, y) using the current pen
`line(x1, y1, x2, y2)` | Plot line from (x1, y1) to (x2, y2)  
`box(x1, y1, x2, y2)` | Draw a filled box bound by (x1, y1) and (x2, y2)  
`path(array of x, y)` | Plot a line from each pair of (x, y) coordinates to the next
`fill(l, t, r, b)` | Fill the area bound by (l, t) and (w - r, h - b), where (w, h) are the dimensions of the canvas 
`clip(l, t, r, b)` | Set clipping bounds to the area within (l, t) and (w - r, h - b), where (w, h) are the dimensions of the canvas

## Basic ASCII shapes

Operation | Description 
--- | --- 
`Rect` | A rectangle object. Dimensions determine if the shape is a square or rectangle
`Oval` | Circle or oval shape. The curve of the oval forms a tangent with every edge of the bounding box  
`Path` | An arbitrary path whose area is bound by the dimensions of the shape  
`AASCIImap` | Like a bitmap, but in ASCII
`ASCIIfont` | ASCIIdraw fonts 
`Sprite` | (description coming)

## ASCIImaps

## ASCIIfonts

##### Basic ASCII operations