package model

import com.fasterxml.jackson.annotation.JsonProperty
import org.jetbrains.annotations.NotNull

class SimpleRequest(@JsonProperty("name") @NotNull val name: String,
                    @JsonProperty("message") @NotNull val message: String)