# compose-and-views-analysis

This project contains the practical implementation that accompanied my Master's thesis: [Analysis of native application user interface development methods in terms of the Android operating system performance and resource usage](https://repozitorij.fer.unizg.hr/islandora/object/fer:13489).

The project contains two applications, _Compose App_ and _Views App_, with identical appearance and behavior.

## Project structure

- `compose-app/`
  - Android app implementation using Jetpack Compose
- `shared/`
  - Shared business logic, models, and resources used by both apps
- `views-app/`
  - Android app implementation using the traditional View system
- `scripts/`
  - Scripts used for testing and evaluation
 
### Scripts

Shell scripts that use ADB (Android Debug Bridge) for testing. These scripts were used to automate running tests and exporting results to logcat or `.csv` files.

- `contentdisplay/`
  - `display_times.sh` - TTID and TTFD measurements
- `memory/`
  - `graphic_memory.sh` - graphic memory measurements
  - `heap_memory.sh` - Java and native heap measurements
- `scroll/`
  - `scroll.sh` - Janky frames and n-th percentile measurements
- `startuptime/`
  - `app_startup_time.sh` - total time and wait time measurements
  - `cold_start.sh` - cold start time measurements
  - `hot_start.sh` - hot start time measurements
  - `warm_start.sh` - warm start time measurements

## License

This repository and all of its content is free to use within the 
[MIT](https://github.com/laura-abramovic/board-games/blob/main/LICENSE)
license.
