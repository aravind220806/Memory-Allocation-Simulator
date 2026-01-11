# Memory Allocation & Compaction Simulator
1. Project Abstract
This project is an interactive simulation of Contiguous Memory Management within an Operating System. It provides a graphical user interface (GUI) to visualize how the system handles process allocation and the resulting fragmentation. A key feature of this simulation is the Compaction (Defragmentation) module, which consolidates non-contiguous free space into a single usable block, demonstrating a critical OS strategy for managing physical RAM.

2. Core Modules
The project is structured into five functional modules:

M1: Memory Initialization: Sets up the physical RAM model using a list of Block objects with defined capacities.

M2: Allocation Engine: Implements search logic (First Fit) to identify suitable memory holes for incoming process requests.

M3: Process Manager: Manages the lifecycle of a process, handling both allocation and deallocation (releasing memory).

M4: Compaction Handler: Uses an Iterator to safely remove scattered free blocks and merge their sizes into one large contiguous free block.

M5: GUI Visualizer: A Java Swing interface that provides real-time, color-coded feedback (Red for Allocated, Green for Free).

3. Features
Visual Memory Map: Real-time rendering of memory blocks showing Process IDs and sizes.

Dynamic Allocation: Input custom process sizes to see if the current memory state can accommodate them.

Interactive Deallocation: Remove processes to create "holes" and observe External Fragmentation.

One-Click Compaction: Solve fragmentation issues instantly by consolidating all free space.

4. Installation & Usage
Prerequisites
Java Development Kit (JDK) 8 or higher.

Running the Simulator
Clone the Repository:

Bash

git clone https://github.com/yourusername/memory-allocation-simulator.git
Compile the Code:

Bash

javac MemoryVisualizer.java
Execute:

Bash

java MemoryVisualizer
5. System Design & Logic
The simulation handles the "effectively final" variable constraint in Java by utilizing an Iterator during the compaction phase. This ensures that the total free memory is calculated accurately while modifying the list structure in real-time.

6. Conclusion
This project demonstrates that while contiguous allocation is efficient for simple tasks, it inevitably leads to fragmentation. The inclusion of the Compaction module highlights the trade-off between memory efficiency and the CPU overhead required to shift processes in physical memory.
