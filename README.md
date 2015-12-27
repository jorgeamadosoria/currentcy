Currentcy
Currentcy is an exchange rate aggregator that basically parses info from public sites belonging to official exchanges.
A list of supported exchanges can be seen here listed alphabetically:
<ul>
<li>Abitab (Cambilex)  http://www.cambilex.com.uy</li>
<li>Altercambio SA  http://www.altercambio.com.uy</li>
<li>BBVa https://www.bbva.com.uy/Inicio/Personas/</li>
<li>Banco Central del Uruguay  http://www.bcu.gub.uy</li>
<li>Banco República  http://www.brou.com.uy</li>
<li>Cambio 18  http://www.cambio18.com</li>
<li>Cambio Aspen  http://www.aspen.com.uy</li>
<li>Cambio SIR http://www.cambiosir.com.uy</li>
<li>Cotización Dólar Uruguay  http://uy.cotizacion-dolar.com</li>
<li>Currency.wiki  http://currency.wiki</li>
<li>Gales SA http://www.gales.com.uy/home/</li>
<li>Google Finance https://www.google.com</li>
<li>Intercambio Idel SA http://www.intercambio.com.uy</li>
<li>Indumex  http://www.indumex.com</li>
<li>Nixus Servicios Financieros  http://www.nixus.com.uy</li>
<li>Cambio Regul SA  http://www.cambioregul.com/portal/hgxpp001.aspx</li>
<li>Yahoo http://finance.yahoo.com</li>
</ul>

This project is just a test ground for technology I like to learn or keep current with, and it is not intended for profit or any other purpose. I'm scratching my own itch here, and if it helps someone else, well, that´s a very nice bonus :)

This is also a test for cloud deployment. I´m using Openshift (openshift.com) from Redhat, which is free for small projects like this as long as it gets used at least once a day. this means that some configuration is externalized in environment variables.

i'm using Spring Tool Suite (Eclipse with EGit). To set this variables on a working copy of these repository:
Go to Run -> Run Configurations -> Environments

add the following variables with values that make sense to your setup:
<ul>
<li>MAIL_PASSWORD</li>
<li>MAIL_USERNAME</li>
<li>OPENSHIFT_MYSQL_DB_HOST</li>
<li>OPENSHIFT_MYSQL_DB_PASSWORD</li>
<li>OPENSHIFT_MYSQL_DB_PORT</li>
<li>OPENSHIFT_MYSQL_DB_USERNAME</li>
</ul>

Mail variables are needed for registering and suscribing to exchange updates. The sender must be a Gmail account (settings for GMail through SSL are hardwired, though changing those is pretty simple). All other variables are Openshift standard variables to connect to a MySQL instance.

Happy coding.


