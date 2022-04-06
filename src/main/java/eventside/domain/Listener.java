package eventside.domain;

public class Listener {
    private String host_port;
    private Class<?> subscribedTo;

    public Listener(String host_port, Class<?> subscribedTo) {
        this.host_port = host_port;
        this.subscribedTo = subscribedTo;
    }

    public String getHost_port() {
        return host_port;
    }

    public Class<?> getSubscribedTo() {
        return subscribedTo;
    }
}
