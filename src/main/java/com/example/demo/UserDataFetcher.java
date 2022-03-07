package com.example.demo;

import com.netflix.dgs.codgen.generated.types.Quote;
import com.netflix.dgs.codgen.generated.types.User;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@DgsComponent
public class UserDataFetcher {

    private final List<User> userData = List.of(
            User.newBuilder()
                    .id(UUID.randomUUID().toString())
                    .username("Elmehdi")
                    .email("Elmehdi@yopmail.com")
                    .quotes(List.of(Quote.newBuilder()
                            .id(UUID.randomUUID().toString())
                            .text("There are no mistakes, only opportunities.")
                            .date(LocalDateTime.now().atZone(ZoneId.of("Africa/Casablanca")).toOffsetDateTime()).build()))
                    .build(),
            User.newBuilder()
                    .id(UUID.randomUUID().toString())
                    .username("Ali")
                    .email("Ali@yopmail.com")
                    .quotes(List.of(Quote.newBuilder()
                            .id(UUID.randomUUID().toString())
                            .text("Whoever is happy will make others happy too.")
                            .date(LocalDateTime.now().atZone(ZoneId.of("Africa/Casablanca")).toOffsetDateTime()).build()))
                    .build()
    );

    @DgsQuery
    public List<User> users() {
        return userData;
    }

    @DgsQuery
    public List<User> filterUserByUsername(@InputArgument String input) {
        if(input == null) { return userData; }
        return userData.stream().filter(s -> s.getUsername().toLowerCase(Locale.ENGLISH).contains(input.toLowerCase(Locale.ENGLISH))).collect(Collectors.toList());
    }

    @DgsQuery
    public User getUserById(@InputArgument String input) throws Exception {
        User user = userData.stream().filter(s -> s.getId().equals(input)).findFirst().get();
        if(input == null) {throw new Exception("User id can't be null");}
        if(user == null) {throw new Exception("User not found with the given id");}
        return user;
    }

    @DgsMutation
    public void addNewUser(@InputArgument User user) {
        userData.add(user);
    }

    @DgsMutation
    public void deleteUser(@InputArgument String input) {
        userData.remove(userData.stream().filter(s -> s.getId().equals(input)).findFirst().get());
    }
}