package course.service;

import course.entity.EntityCardTypes;
import course.entity.EntityCards;
import course.entity.EntityUsers;

public class CardsService {

    public void createCard(EntityUsers user, EntityCardTypes cardType) {
        EntityCards card = new EntityCards(user, cardType);

    }
}
