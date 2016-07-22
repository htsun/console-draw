package com.harrison.springer.model.action;

import com.harrison.springer.model.Canvas;

public interface Action {

    Canvas applyOnto(Canvas canvas);
}
