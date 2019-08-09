class Pathfind {
    //A* path-finding algorithm

    private Node[][] nodeGrid;
    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;

    private float cost, diagonalCost;

    public Pathfind(Node[][] nodeGrid) {

        this.openList = new ArrayList<>();
        this.closedList = new ArrayList<>();

        this.cost = 1;
        this.diagonalCost = 1.4f;

        this.nodeGrid = nodeGrid;
    }

    //Returns most efficient path as ArrayList
    public ArrayList<Node> findPath(Node startNode, Node goalNode) {
        this.openList.clear();
        this.closedList.clear();

        //Make each node's parent null.
        for (int i = 0; i < this.nodeGrid.length; i++) {
            for (int j = 0; j < this.nodeGrid[0].length; j++) {
                this.nodeGrid[i][j].setParent(null);
            }
        }

        this.openList.add(startNode);

        //Continue looping until openList is empty
        while (!openList.isEmpty()) {

            //Sort open list by F
            openList.sort((node1, node2) -> Float.compare(node1.getF(), node2.getF()));
            Node currentNode = openList.get(0);

            openList.remove(currentNode);
            closedList.add(currentNode);

            if (!currentNode.isWalkable()) continue;

            //IF GOAL REACHED
            if (currentNode == goalNode) {
                return constructPath(currentNode);
            }

            ArrayList<Node> children = getAdjacentNodes(currentNode);

            //Loop through all of the children of the current node
            for (Node child : children) {

                //If child is in the closed list, skip it.
                if (closedList.contains(child)) continue;

                //If child is diagonal, use diagonal cost. Else, use regular cost.
                float cost = (currentNode.getX() != child.getX() && currentNode.getY() != child.getY()) ? this.diagonalCost : this.cost;
                float tentativeGScore = currentNode.getG() + cost;

                if (!openList.contains(child)) {
                    openList.add(child);
                } else if (tentativeGScore >= child.getG()) {
                    continue;
                }

                child.setParent(currentNode);
                child.setG(tentativeGScore);
                child.setH(Math.abs(child.getX() - goalNode.getX()) + Math.abs(child.getY() - goalNode.getY()));
                child.setF();
            }
        }

        return new ArrayList<>();
    }

    private ArrayList<Node> constructPath(Node constructFrom) {
        ArrayList<Node> path = new ArrayList<>();

        while (true) {
            path.add(constructFrom);

            if (constructFrom.getParent() == null) {
                break;
            }

            constructFrom = constructFrom.getParent();
        }

        Collections.reverse(path);

        return path;
    }

    private ArrayList<Node> getAdjacentNodes(Node node) {

        ArrayList<Node> adjacentNodes = new ArrayList<>();
        //Loops through adjacent nodes
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {

                //skip iteration x is 0 and y is 0
                if (x == 0 && y == 0) {
                    continue;
                }

                if (node == null) { /* Return now if node is null */
                    System.out.println("Null node supplied to getAdjacentNodes()"); /* Debug information */
                    return adjacentNodes;
                }

                int newX = node.getX() + x;
                int newY = node.getY() + y;

                if (validateCoordinates(newX, newY)) {
                    adjacentNodes.add(nodeGrid[newX][newY]);
                }
            }
        }
        return adjacentNodes;
    }

    private boolean validateCoordinates(int x, int y) {
        if (x >= 0 && x < nodeGrid.length) { //x axis
            if (y >= 0 && y < nodeGrid[x].length) { //y axis
                return true;
            }
        }
        return false;
    }
}