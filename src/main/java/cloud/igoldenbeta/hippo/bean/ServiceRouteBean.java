package cloud.igoldenbeta.hippo.bean;

/**
 * Created by hanruofei on 16/8/11.
 */
public class ServiceRouteBean {

    private Long id;
    private String serviceName;
    private String serviceMethod;
    private String serviceHost;
    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceMethod() {
        return serviceMethod;
    }

    public void setServiceMethod(String serviceMethod) {
        this.serviceMethod = serviceMethod;
    }

    public String getServiceHost() {
        return serviceHost;
    }

    public void setServiceHost(String serviceHost) {
        this.serviceHost = serviceHost;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ServiceRouteBean{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", serviceMethod='" + serviceMethod + '\'' +
                ", serviceHost='" + serviceHost + '\'' +
                ", type=" + type +
                '}';
    }
}
