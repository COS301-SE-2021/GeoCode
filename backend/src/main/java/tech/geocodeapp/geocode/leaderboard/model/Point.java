package tech.geocodeapp.geocode.leaderboard.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

import org.hibernate.annotations.Cascade;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.user.model.User;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Point
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-05T13:14:20.256Z[GMT]")

@Entity
@Table(name = "point")
public class Point   {
  @Id
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("amount")
  private Integer amount = null;

  @JsonProperty("user")
  @OneToOne
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private User user = null;

  @JsonProperty("leaderboard")
  @OneToOne
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private Leaderboard leaderboard = null;

  public Point id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Point amount(Integer amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * minimum: 0
   * @return amount
   **/
  @Schema(required = true, description = "")
      @NotNull

  @Min(0)  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public Point user(User user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Point leaderboard(Leaderboard leaderboard) {
    this.leaderboard = leaderboard;
    return this;
  }

  /**
   * Get leaderboard
   * @return leaderboard
   **/
  @Schema(description = "")
  
    @Valid
    public Leaderboard getLeaderBoard() {
    return leaderboard;
  }

  public void setLeaderBoard(Leaderboard leaderboard) {
    this.leaderboard = leaderboard;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Point point = (Point) o;
    return Objects.equals(this.id, point.id) &&
        Objects.equals(this.amount, point.amount) &&
        Objects.equals(this.user, point.user) &&
        Objects.equals(this.leaderboard, point.leaderboard);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, amount, user, leaderboard);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Point {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    leaderboard: ").append(toIndentedString(leaderboard)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
