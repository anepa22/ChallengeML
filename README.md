Ejercicio / Challenge para ML

No se se si es la manera más académica, pero lo resolví así. La base está hecha a lo Roberto (O sea todo en una tabla.) en Oracle XE.
No es necesario crear scrip de creación de tablas ya que este hecho con JPA

Con el archivo preuba-sis-sol.html se puede probar la posición de los planetas para un día en especial. Es en javascript y consume los servicios siguientes.

Esta realizado con SpringBoot y con una base local Oracle XE

El ejercicio está en el archivo Challenge_Monitoreo_MELI dentro del proyecto.

Espero que a alguien le sirva y así poder agilizar nuestras neuronas.

Renegué bastante con los double ya que el sin 90 no da 0 por el punto flotante y tuve que redondear valores para poder igualar pendientes.
El que quiera optimizarlo es libre de realizarlo.

Contiene Swagger: http://localhost:8080/swagger-ui.html
Swagger

Servicios
http://localhost:8080

GET
/sissol/getAllDias/getAllDias

GET
/sissol/getClimaDelDia/getClimaDelDia

GET
/sissol/getdia/getdia

GET
/sissol/getdiaMasLluvioso/getdiaMasLluvioso

GET
/sissol/getdiasConLluvia/getdiasConLluvia

GET
/sissol/getDiasConOptimaPresionYTemp/getDiasConOptimaPresionYTemp

GET
/sissol/getDiasConSequia/getDiasConSequia
