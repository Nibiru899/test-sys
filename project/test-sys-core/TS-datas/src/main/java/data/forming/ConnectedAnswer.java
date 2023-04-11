package data.forming;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue(value = "connected")
public class ConnectedAnswer extends Competity{
    @OneToOne(mappedBy = "connected_id")
    @JoinColumn(name = "connected_id")
    private ConnectedAnswer connectedAnswer;

    public ConnectedAnswer getConnectedAnswer() {
        return connectedAnswer;
    }

    public void setConnectedAnswer(ConnectedAnswer connectedAnswer) {
        this.connectedAnswer = connectedAnswer;
    }
}
