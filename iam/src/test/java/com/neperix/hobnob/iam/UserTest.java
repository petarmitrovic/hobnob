package com.neperix.hobnob.iam;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author petarmitrovic
 */
public class UserTest {

    @Test
    public void itShouldSetEmptySetIfNullIsPassed() {

        User user = User.builder().build();
        user.setRoles(null);

        Assert.assertThat(user.getRoles(), Matchers.hasSize(0));
    }

    @Test
    public void itShoudSetEmptySetIfEmptyIsPassed() {
        User user = User.builder().build();
        user.setRoles(Collections.emptySet());

        Assert.assertThat(user.getRoles(), Matchers.hasSize(0));
    }

    @Test
    public void itShouldSetStringValuesSetIfNonEmptyLongSetIsPassed() {
        User user = User.builder().build();
        user.setRoles(new HashSet<>(Arrays.asList(1L, 2L, 3L)));

        Assert.assertThat(user.getRoles(), Matchers.hasSize(3));
        Assert.assertThat(user.getRoles(), Matchers.containsInAnyOrder("1", "2", "3"));
    }
}
