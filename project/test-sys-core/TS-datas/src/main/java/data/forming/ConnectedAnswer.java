package data.forming;


import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "connected")
public class ConnectedAnswer extends Answer{
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "connected_id")
    @JoinColumn(name = "connected_id")
    private ConnectedAnswer connectedAnswer;

    public ConnectedAnswer getConnectedAnswer() {
        return connectedAnswer;
    }

    public void setConnectedAnswer(ConnectedAnswer connectedAnswer) {
        this.connectedAnswer = connectedAnswer;
    }
}
