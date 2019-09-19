package com.vinegrad.fitnessclub.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="memberships")
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private MembershipType type;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;
    @ManyToOne(targetEntity = Member.class)
    private Member member;

    public Membership(MembershipType type, LocalDate startDate) {
        this.type = type;
        this.startDate = startDate;
        this.endDate = startDate.plusDays(type.getDuration());
    }

    public Membership() {

    }

    public Long getId() {
        return id;
    }

    public MembershipType getType() {
        return type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Membership that = (Membership) o;
        return Objects.equals(id, that.id) &&
                type == that.type &&
                startDate.equals(that.startDate) &&
                endDate.equals(that.endDate);
    }

    public static Membership of(String membershipType, LocalDate startDate) {
        for(MembershipType type : MembershipType.values()) {
            if(type.getDisplay().toLowerCase().equals(membershipType.toLowerCase())) {
                return new Membership(type, startDate);
            }
        }
        System.out.println(":0:0:0");
        return null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, startDate, endDate);
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
