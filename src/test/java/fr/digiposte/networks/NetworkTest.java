package fr.digiposte.networks;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class NetworkTest {

  @Test
  void packets_should_be_forwarded_by_any_cisco_router() {
    Network network = new Network("cisco White", "cisco Black", "cisco Blue")
        .from("White").to("Black")
        .from("Black").to("Blue");

    network.send("hello").to("White");
    assertThat(network.stateOf("White")).isEqualTo("hello");

    network.hop();

    assertThat(network.stateOf("White")).isEqualTo("");
    assertThat(network.stateOf("Black")).isEqualTo("hello");

    network.hop();

    assertThat(network.stateOf("Black")).isEqualTo("");
    assertThat(network.stateOf("Blue")).isEqualTo("hello");
  }

}
