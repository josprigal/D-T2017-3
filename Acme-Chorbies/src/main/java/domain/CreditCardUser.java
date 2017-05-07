package domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public abstract class CreditCardUser extends Actor {

    private CreditCard creditCard;
    private Collection<Chirp> chirpsReceived;
    private Collection<Chirp>	chirpsSents;

    @OneToOne(mappedBy = "creditCardUser")
    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @OneToMany(mappedBy = "recipent")
    public Collection<Chirp> getChirpsReceived() {
        return this.chirpsReceived;
    }

    public void setChirpsReceived(final Collection<Chirp> chirpsReceived) {
        this.chirpsReceived = chirpsReceived;
    }

    @OneToMany(mappedBy = "sender")
    public Collection<Chirp> getChirpsSents() {
        return this.chirpsSents;
    }

    public void setChirpsSents(final Collection<Chirp> chirpsSents) {
        this.chirpsSents = chirpsSents;
    }
}
