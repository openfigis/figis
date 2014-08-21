
1) monitor.csv
Is the file compiled from http://figisapps.fao.org/FIGISwiki/index.php/FIGIS_monitoring

2) figis-jmeter.jmx on www.fao.org
The file with the tests, recorded from FireFox
934 samples, AVG 93ms, ERROR 38,22%


3) figis-jmeter-without-error.jmx on www.fao.org
Alle requests from (2) minus the failed requests
578 samples, AVG 122ms, ERROR 0%


4)figis-jmeter-8080.jmx on http://hqldvfigis1:8080/fishery
26 samples, AVG 12131ms, ERROR 46%
