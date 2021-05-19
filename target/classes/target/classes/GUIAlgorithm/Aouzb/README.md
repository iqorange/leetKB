# AouZB 乐感培养系统
##### Music Game Proj
###### Version 0.1.1 (In development)
这是一个主要使用JAVA开发的大屏-多人互动音乐游戏系统，使用socket、websocket、Java swing、web前端技术、多线程与相关线程安全技术、servlet等后端技术、相关数据结构与算法，在讨论多种方式开发的音游demo系统。这是一个多人竞赛的游戏系统，16名玩家对应相应的小圆点操控方向并随着音乐节奏移动，移动时会留下响应颜色的地毯，由地毯面积计算分数和排名。  

## Main Proj  
### Client (In development)  
游戏的主体文件，用于在整个游戏交互中作为效果大屏使用，为玩家提供直观的反馈效果  

### Server (plan)  
游戏的服务端文件，使用tomcat跑在服务器上，用于连接玩家与游戏大屏展示平台，使用fastjson进行数据通信  

### Card (plan)  
玩家的操纵面板，使用前端技术开发，通过vue、axios等技术与服务端进行通信，让玩家操控游戏里的小圆球运动方向并实时查看分数排名  
  
## UML  
### User Case 用例图  
![用例图](https://xstxs.top/UserCase.jpg)  
  
### Class 类图
![类图](https://xstxs.top/classuml.jpg)  
  
## WBS
### 甘特图
![甘特图](https://xstxs.top/AouZBGT.jpg)  
  
### 资源图
![资源图](https://xstxs.top/AouZBZY.jpg)  
  
### 网络图
![网络图](https://xstxs.top/AouZBWL.jpg)  
  
开发等技术原因UML与WBS仅供参考哦~  
后续可能将继续优化  
