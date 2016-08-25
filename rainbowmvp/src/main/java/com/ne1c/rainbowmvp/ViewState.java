/*
 * Copyright 2016 Nikolay Kucheriaviy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ne1c.rainbowmvp;

/**
 * A enum that contains possible states for view
 *
 * START - use for start any operations that need to show for user
 * IN_PROGRESS - use for show progress of any operation
 * FINISH - use in end of work any operation
 * SUCCESS - use for show result of any operation
 * ERROR - use for show error of any operation
 * EMPTY - use for default state if not necessary show any information
 */
public enum ViewState {
    START, IN_PROGRESS, FINISH, SUCCESS, ERROR, EMPTY
}
