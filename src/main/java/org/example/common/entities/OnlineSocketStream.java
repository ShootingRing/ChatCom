package org.example.common.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Getter
@Setter
@Builder
public class OnlineSocketStream {
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
}
