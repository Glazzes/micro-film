package jvm.glaze.clients.movie;

import java.time.LocalDateTime;

public record MovieResponse(Status status, LocalDateTime timeStamp){}
