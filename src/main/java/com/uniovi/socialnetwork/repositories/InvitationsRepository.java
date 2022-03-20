package com.uniovi.socialnetwork.repositories;

import com.uniovi.socialnetwork.entities.Invitation;
import com.uniovi.socialnetwork.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvitationsRepository extends CrudRepository<Invitation, Long> {

    @Query("SELECT i FROM Invitation i WHERE i.receiver = ?1 ORDER BY i.id ASC")
    Page<Invitation> findAllByUser(Pageable pageable, User user);

    @Query("SELECT i FROM Invitation i WHERE (i.receiver = ?1 AND i.sender = ?2) OR (i.receiver = ?2 AND i.sender = ?1)")
    List<Invitation> deleteInvitationsBetween(User user1, User user2);
}
