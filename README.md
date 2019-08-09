# Java A* Pathfinding Algorithm

A reusable a* pathfinding algorithm written in Java.

## Usage

```java
public static void main(String[] args){
    //Create path-finding grid of nodes
    int GRID_WIDTH = 15;
    int GRID_HEIGHT = 15;
    Node[][] nodeGrid = new Node[GRID_WIDTH][GRID_HEIGHT];

    //Populate path-finding grid of nodes
    for (int i = 0; i < nodeGrid.length; i++) {
        for (int j = 0; j < nodeGrid[i].length; j++) {
            nodeGrid[i][j] = new Node(i, j, true);
        }
    }

    //Path-finding start node
    Node START_NODE = nodeGrid[1][3];

    //Path-finding end node
    Node END_NODE = nodeGrid[7][10];
        
    //Create pathfind object
    Pathfind pathfind = new Pathfind(nodeGrid);
        
    //Find path
    ArrayList<Node> path = pathfind.findPath(START_NODE, END_NODE);
}
```

## License
[MIT](https://choosealicense.com/licenses/mit/)