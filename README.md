Grafana: http://localhost:3000/ 
grafana listens Prometheus server http://localhost:9090/

Grafana runs as service in Windows (Control Panel -> Admin -> Services)
Prometheus runs from cmd
C:\Users\df773\Downloads\prometheus\prometheus> prometheus.exe --config.file=prometheus.yml

Prometheus -> Query -> Graph -> Search your metric -> Execute
Grafana -> New dashboard -> Metrics -> Run queries


OTHER NOTES: 

Addition in prometheus.yml

- job_name: 'spring-boot-app'

  static_configs:
    - targets: ['localhost:8080']

  metrics_path: 'actuator/prometheus'