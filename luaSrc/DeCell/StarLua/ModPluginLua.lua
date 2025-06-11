-- TYPE MOD_PLUGIN
function onApplicationLoad()
    print(" ____  _             _                   _                    _          _ ")
    print("/ ___|| |_ __ _ _ __| |   _   _  __ _   | |    ___   __ _  __| | ___  __| |")
    print("\\___ \\| __/ _` | '__| |  | | | |/ _` |  | |   / _ \\ / _` |/ _` |/ _ \\/ _` |")
    print(" ___) | || (_| | |  | |__| |_| | (_| |  | |__| (_) | (_| | (_| |  __/ (_| |")
    print("|____/ \\__\\____|_|  |_____\\____|\\____|  |_____\\___/ \\____|\\____|\\___|\\____|")
end

-- these are all the possible functions

function afterGameSave()
end

function beforeGameSave()
end

function onGameSaveFailed()
end

function onEnabled(wasEnabledBefore)
end

function onGameLoad(newGame)
    print("ON_GAME_LOAD_LUA")
end

function onNewGame()
end

function onNewGameAfterEconomyLoad()
end

function onNewGameAfterTimePass()
end

function configureXStream(x)
end

function onNewGameAfterProcGen()
end

function onDevModeF8Reload()
end

function onAboutToStartGeneratingCodex()
end

function onAboutToLinkCodexEntries()
end

function onCodexDataGenerated()
end
