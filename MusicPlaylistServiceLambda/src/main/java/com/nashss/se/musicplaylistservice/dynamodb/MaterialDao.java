package com.nashss.se.musicplaylistservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.nashss.se.musicplaylistservice.dynamodb.models.Material;
import com.nashss.se.musicplaylistservice.exceptions.MaterialNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Accesses data for a task using {@link Material} to interact with the model in DynamoDB.
 */

@Singleton
public class MaterialDao {
    private final DynamoDBMapper mapper;

    /**
     * Instantiates a MaterialDao object.
     *
     * @param mapper the {@link DynamoDBMapper} used to interact with the album_track table
     */

    @Inject
    public MaterialDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Retrieves a material by orgId and materialId.
     *
     * If not found, throws MaterialNotFoundException.
     *
     * @param orgId The orgId to look up
     * @param materialId The materialId to look up
     * @return The corresponding Material if found
     */
    public Material loadSingleMaterial(String orgId, String materialId) {
        Material material = mapper.load(Material.class, orgId, materialId);
        if (material == null) {
            throw new MaterialNotFoundException(String.format("Could not find material with orgId %s and materialId %s", orgId, materialId));
        }
        return material;
    }

    /**
     * Retrieves all Materials matching provided orgId.
     *
     * If none found, returns an empty list.
     *
     * @param orgId The orgId to look up
     * @return A list of Materials found, if any
     */
    public List<Material> loadMaterialsForOrg(String orgId) {
        Material material = new Material();
        material.setOrgId(orgId);
        DynamoDBQueryExpression<Material> queryExpression = new DynamoDBQueryExpression<Material>()
                .withHashKeyValues(material);
        return mapper.query(Material.class, queryExpression);
    }


    /**
     * Saves provided Material to DynamoDB to create or update DynamoDB record.
     *
     * @param material The Material to be saved
     */
    public void writeMaterial(Material material) {
        mapper.save(material);
    }

    /**
     * Removes the provided Material from DynamoDB, if present.
     *
     * @param material The Material to be deleted
     */
    public void deleteMaterial(Material material) {
        mapper.delete(material);
    }

}
