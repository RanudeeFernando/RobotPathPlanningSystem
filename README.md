# Robot Path Planning using A* Algorithm

## 📌 Overview
This project focuses on programming a robot to navigate a **grid-based environment**, finding the optimal path from a start point to a goal point while avoiding obstacles. The **A* search algorithm** is used for path planning due to its efficiency and heuristic-driven approach.

## 🚀 Features
- Customizable grid size (3x3 to 50x50)
- Adjustable obstacle density (0.0 to 1.0)
- Robot movement in **8 directions** (vertically, horizontally, and diagonally)
- **A* Algorithm implementation** for optimal pathfinding
- Data structure optimizations for improved performance
- **Test plans** to ensure functionality

## 📚 Algorithm Selection: A* Search
The **A* Algorithm** was chosen because:
✔ It **always finds the optimal path** if one exists.  
✔ It is more efficient than BFS, DFS, and Dijkstra’s Algorithm.  
✔ Uses **Euclidean distance** as the heuristic for cost estimation.  

## 🛠 Data Structures Used
- **2D Array** → Represents the grid.
- **Custom Linked List** → Manages the open list (priority queue behavior).
- **Custom Array List** → Stores visited cells (closed list) efficiently.

## 🎥 Demo Video
<iframe width="560" height="315" src="https://www.youtube.com/embed/https://youtu.be/r5lKiIhQjfo" 
title="YouTube Video Player" frameborder="0" allowfullscreen></iframe>


## 🏗 Installation & Setup
### **Prerequisites**
- Java installed on your system (JDK 8+ recommended)
- IDE like **IntelliJ IDEA, Eclipse, or VS Code**

### **Steps to Run the Project**
1. **Clone the Repository**  
   ```bash
   git clone https://github.com/your-username/repository-name.git
   cd repository-name
