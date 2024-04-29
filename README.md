# java-billing-utility

Mini java project to extract document events and policy number from Grafana log.
A simple tool to extract document events and policy number from Grafana log.
This is particularly useful for billing team to extract policy number and document events from Grafana log.
This is only applicable to logs extracted from Grafana dashboard -
https://g3-prod-api-grafana.escloud.co.uk/d/5r09Y7NIz/billing-document-generation-received-vs-sent-app-enrichment-service-billing-docs?orgId=1&from=now-24h&to=now
Steps for using this -
1 - Extract log panel data using the Panel header drop down option > inspect > data tab > Download logs
2 - From the downloaded txt file, remove meta-data (if any) and keep only the log data
3 - Put the txt file in the resources folder of this project with the correct name convention
4 - Run the main class - Main.java
5 - Output will be generated in the resources folder with the suffix of converted