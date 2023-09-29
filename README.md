# Easy Learn

![image-20230929020756708](/Users/evenx/Library/Application Support/typora-user-images/image-20230929020756708.png)

![image-20230929020911354](/Users/evenx/Library/Application Support/typora-user-images/image-20230929020911354.png)

![image-20230929020959210](/Users/evenx/Library/Application Support/typora-user-images/image-20230929020959210.png)

![image-20230929021051513](/Users/evenx/Library/Application Support/typora-user-images/image-20230929021051513.png)

![image-20230929021159002](/Users/evenx/Library/Application Support/typora-user-images/image-20230929021159002.png)



Adopt B2C business model, use front and back end separation development mode. The project includes the background management system and the front user system, and the two systems contain the back-end interface part and the front page part respectively.

## Frontend

- Utilized the popular front-end framework Vue, adhering to the Es6 development standards.
- Employed a modular development approach.
- Set up the page environment using the Nuxt framework and the vue-admin-template template.
- Utilized Element-ui for page layout.
- Managed dependencies in the front-end environment using Npm.
- Employed Babel for code transformation.
- Utilized Webpack for static resource bundling.
- Utilized axios for making Ajax requests.
- Implemented ECharts for data visualization.

## Backend

- 
  SpringBoot+SpringCloud is used for microservice architecture, and Feign, Gateway, Hystrix, Alibaba's Nacos and other components are used to build the basic environment of the project. 
- The project also uses MyBatisPlus for persistence layer operation, uses OAuth2+JWT for distributed access, and integrates SpringSecurity for permission control. In addition, Alibaba's EasyExcel was used in the project to achieve Excel read and write operations.
- Redis was used for home page data caching, Git was used for code version control, and Swagger was integrated to generate interface documents.
- Based on kubernetes cluster, integrated kubesphere visual interface to build a complete system environment. Use Cluster deployment, including Redis Cluster cluster, MySQL master-slave and sub-database sub-table (using ShardingSphere) cluster, RabbitMQ mirroring queue cluster, ElasticSearch high availability cluster. Based on kubesphere integration of Jenkins full visual CICD, full Pipeline pipeline writing, parametric construction + manual confirmation mode guarantee.