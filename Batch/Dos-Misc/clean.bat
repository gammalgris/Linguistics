@Echo off


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void main()
@rem ---
@rem ---   Deletes all directories within this project which contain temporary data
@rem ---   (e.g. compiler output, log files, etc.).
@rem ---

:main

	call:defineMacros
	call:setUp

	echo.
	echo project dir .... %projectDir%
	echo.

	for /L %%i in (1, 1, %directories.length%) do (

		setlocal EnableDelayedExpansion

			call:deleteDirectory !directories[%%i]!

		endlocal
	)

	for /L %%i in (1, 1, %files.length%) do (

		setlocal EnableDelayedExpansion

			call:deleteFile !files[%%i]!

		endlocal
	)

	call:tearDown

	pause

%return%


@rem ================================================================================
@rem ===
@rem ===   Internal Subroutines
@rem ===

@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void defineMacros()
@rem ---
@rem ---   The subroutine defines required macros.
@rem ---

:defineMacros

	set "ifError=set foundErr=1&(if errorlevel 0 if not errorlevel 1 set foundErr=)&if defined foundErr"
	
	set "cprintln=echo"
	set "cprint=echo|set /p="
	
	set "return=exit /b"

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void setUp()
@rem ---
@rem ---   Declares and initializes variables and constants.
@rem ---

:setUp

	set projectDir=%~dp0..\..\

	set directories.length=11

	set directories[1]=%projectDir%Sources\.data\
	set directories[2]=%projectDir%Sources\Common\classes\
	set directories[3]=%projectDir%Sources\Common-Tests\classes\
	set directories[4]=%projectDir%Sources\Dictionary\classes\
	set directories[5]=%projectDir%Sources\Dictionary-Tests\classes\
	set directories[6]=%projectDir%Sources\Neural\classes\
	set directories[7]=%projectDir%Sources\Neural-Tests\classes\
	set directories[8]=%projectDir%Sources\Parser\classes\
	set directories[9]=%projectDir%Sources\Parser-Tests\classes\
	set directories[10]=%projectDir%Sources\Prolog\classes\
	set directories[11]=%projectDir%Sources\Prolog-Tests\classes\


	set files.length=2

	set files[1]=%projectDir%Sources\Prolog-Tests\literal-serialization-test.pl
	set files[2]=%projectDir%Sources\Prolog-Tests\literal-serialization-2-test.pl

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void tearDown()
@rem ---
@rem ---   Declared variables and constants are cleaned up.
@rem ---

:tearDown

	for /L %%i in (1, 1, %directories.length%) do (

		set directories[%%i]=
	)

	set directories.length=


	for /L %%i in (1, 1, %files.length%) do (

		set files[%%i]=
	)

	set files.length=


	set projectDir=

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void deleteDirectory(String _path)
@rem ---
@rem ---   Deletes the specified directory and all its content.
@rem ---
@rem ---
@rem ---   @param _path
@rem ---          the specified path represents a directory
@rem ---

:deleteDirectory

	set "_path=%1"
	if '%_path%'=='' (

		echo No directory has been specified! >&2
		%return% 2
	)
	set "_path=%_path:"=%"


	if exist %_path% (

		echo deleting %_path%
		rmdir /S /Q %_path%

	) else (

		echo %_path% doesn't exist.
	)


	set _path=

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void deleteFile(String _path)
@rem ---
@rem ---   Deletes the specified file.
@rem ---
@rem ---
@rem ---   @param _path
@rem ---          the specified path represents a file
@rem ---

:deleteFile

	set "_path=%1"
	if '%_path%'=='' (

		echo No file has been specified! >&2
		%return% 2
	)
	set "_path=%_path:"=%"


	if exist %_path% (

		echo deleting %_path%
		del /F /Q %_path%

	) else (

		echo %_path% doesn't exist.
	)


	set _path=

%return%
