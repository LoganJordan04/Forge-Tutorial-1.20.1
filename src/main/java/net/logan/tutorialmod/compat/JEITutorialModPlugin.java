package net.logan.tutorialmod.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.logan.tutorialmod.Recipe.GemPolishingRecipe;
import net.logan.tutorialmod.TutorialMod;
import net.logan.tutorialmod.screen.GemPolishingStationScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEITutorialModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(TutorialMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new GemPolishingCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<GemPolishingRecipe> polishingRecipes = recipeManager.getAllRecipesFor(GemPolishingRecipe.Type.INSTANCE);
        
        registration.addRecipes(GemPolishingCategory.GEM_POLISHING_TYPE, polishingRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(GemPolishingStationScreen.class, 80, 30, 15, 30,
                GemPolishingCategory.GEM_POLISHING_TYPE);
    }
}
