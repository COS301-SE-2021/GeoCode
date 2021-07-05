package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Point
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-05T09:14:58.803Z[GMT]")


public class Point   {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("amount")
  private Integer amount = null;

  @JsonProperty("userId")
  private UUID userId = null;

  @JsonProperty("leaderBoardId")
  private UUID leaderBoardId = null;

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
   * @return amount
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public Point userId(UUID userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public Point leaderBoardId(UUID leaderBoardId) {
    this.leaderBoardId = leaderBoardId;
    return this;
  }

  /**
   * Get leaderBoardId
   * @return leaderBoardId
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public UUID getLeaderBoardId() {
    return leaderBoardId;
  }

  public void setLeaderBoardId(UUID leaderBoardId) {
    this.leaderBoardId = leaderBoardId;
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
        Objects.equals(this.userId, point.userId) &&
        Objects.equals(this.leaderBoardId, point.leaderBoardId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, amount, userId, leaderBoardId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Point {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    leaderBoardId: ").append(toIndentedString(leaderBoardId)).append("\n");
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
