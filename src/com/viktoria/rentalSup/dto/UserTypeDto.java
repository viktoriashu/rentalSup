package com.viktoria.rentalSup.dto;

import java.util.Objects;

public class UserTypeDto {
    private final Long id;
    private final String description;

    public UserTypeDto(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTypeDto that = (UserTypeDto) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "UserTypeDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
